theme: /

    state: newNode_0
        a: Назовите страну, а я назову ее президента
        go!: /newNode_1
    @IntentGroup
        {
          "boundsTo" : "/newNode_0",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_1
        state: 1
            intent: /sys/aimylogic/ru/country
            intent: /newNode_1/1

            go!: /newNode_3

        state: 2
            intent: /newNode_1/2

            go!: /newNode_12

        state: Fallback
            event: noMatch
            go!: /newNode_2
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_1",
                name: "newNode_1 buttons",
                handler: function($context) {
                }
            });

    state: newNode_2
        a: Вы не назвали страну
        # Transition /newNode_9
        go!: /newNode_7

    state: newNode_3
        HttpRequest:
            url = https://query.wikidata.org/sparql?format=json&query=SELECT DISTINCT ?headOfStateLabel WHERE {   ?country wdt:P31 wd:Q3624078;     rdfs:label ?countryLabel.     FILTER REGEX (?countryLabel, "${COUNTRY.name}")     OPTIONAL { ?country wdt:P35 ?headOfState } .     SERVICE wikibase:label { bd:serviceParam wikibase:language "ru"  } }
            method = GET
            body = 
            okState = /newNode_5
            errorState = /newNode_4
            timeout = 0
            headers = []
            vars = []

    state: newNode_4
        a: Произошла ошибка
        # Transition /newNode_10
        go!: /newNode_7

    state: newNode_12
        a: Назаров Владимир Юрьевич
        # Transition /newNode_13
        go!: /newNode_7

    state: newNode_5
        script:
            if ($session.httpResponse
                && $session.httpResponse.results
                && $session.httpResponse.results.bindings
                && $session.httpResponse.results.bindings[0]
                && $session.httpResponse.results.bindings[0].headOfStateLabel
                && $session.httpResponse.results.bindings[0].headOfStateLabel.value) 
            {
                $session.president = $session.httpResponse.results.bindings[0].headOfStateLabel.value;
            } else {
                $session.president = null;
            }
        if: $session.president
            go!: /newNode_6
        else:
            go!: /newNode_7

    state: newNode_6
        a: Президент - {{$session.president}}
        # Transition /newNode_8
        go!: /newNode_7

    state: newNode_7
        a: Попробуйте еще раз
        # Transition /newNode_11
        go!: /newNode_0
