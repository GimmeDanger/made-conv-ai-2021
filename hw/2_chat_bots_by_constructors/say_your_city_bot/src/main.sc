require: city/city.sc
    module = sys.zb-common

theme: /

    state: Start
        q!: $regex</start>
        a: Привет!
    
    
    state: UserGreet
        intent!: /привет
        a: Из какого ты города?
        go!: /GetCity
        
    

    state: GetCity
        state:
            q: * $City *
            a: Говорят, {{ $parseTree._City.name }} очень красивый город!
        state:
            event: noMatch
            a: Я не знаю такой город. Попробуй еще раз


    state: End
        intent!: /пока
        a: Пока!


    state:
        event: noMatch
        a: Я вас не понимаю
