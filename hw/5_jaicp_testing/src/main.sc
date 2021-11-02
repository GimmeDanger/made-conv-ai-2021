require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
require: localPatterns.sc
  

theme: /

    state: Welcome
        q!: $regex</start>
        q: $regex</start> || fromState = /AskPhone, onlyThisState = true
        q!: * $hi *
        script:
            $jsapi.startSession();
        random:
            a: Здравствуйте!
            a: Приветствую!
        a: Меня зовут {{capitalize($injector.botName)}}.
        go!: /SuggestHelp
            
    state: CatchAll || noContext = true
        event!: noMatch
        a: Простите, я не поняла. Переформулируйте, пожалуйста, свой запрос.
        
    state: SuggestHelp
        q: отмена || fromState = /AskPhone, onlyThisState = true
        a: Давайте я помогу вам купить билет на самолет, хорошо?
        buttons:
            "Да"
            "Нет"
        
        state: Accepted
            q: * (да/давай*/отлично) *
            a: Отлично!
            if: $client.phone
                go!: /ConfirmPhone
            else:
                go!: /AskPhone
            
        state: Rejected
            q: * (не/нет) *
            a: Боюсь, что ничего другого я пока предложить не могу...
            
    state: AskPhone || modal = true
        a: Для продолжения напишите, пожалуйста, мне ваш номер телефона.
        buttons:
            "Отмена"
            
        state: Get
            q: * @duckling.phone-number *
            script:
                log("@@@@@@@@@@@@@@@ " + toPrettyString($parseTree));
            go!: /ConfirmPhone
            
    state: ConfirmPhone
        script:
            $temp.phone = $parseTree["_duckling.phone-number"] || $client.phone;
        a: Ваш номер {{$temp.phone}}, верно?
        script:
            $session.probablyPhone = $temp.phone;
        buttons:
            "Да"
            "Нет"
            
        state: Yes
            q: * (да/верно) *
            script:
                $client.phone = $session.probablyPhone;
                delete $session.probablyPhone;
            a: Хорошо.
            go!: /DiscountInform
            
        state: No
            q: (нет/не [верно])
            go!: /AskPhone
            
    state: DiscountInform
        script:
            var nowDate = $jsapi.dateForZone("Europe/Moscow", "dd.MM");
            
            var answerText = "Хочу отметить, что вам крупно повезло! Сегодня (" + nowDate + ") действует акция!";
            var discount = "Купите билет сегодня и получите скидку в 10% на следующую покупку!";
            
            $reactions.answer(answerText);
            $reactions.answer(discount);

            
    state: Goodbye
        q!: * (стоп/хватит/{(все/достаточн*) * ([на] сегодня)}/достаточн*) *
        random:
            a: Позовите меня, когда захотите отправиться в путешествие!
            a: Позовите меня, если захотите быстро купить билеты на самолет!
        script:
            $response.replies = $response.replies || [];
            $response.replies
                .push({
                    type: "raw",
                    body: {
                        end_session: true
                    }
                });
            $jsapi.stopSession();
                
    state: Reset
        q!: reset   
        script:
            $client = {};
        go!: /Welcome
