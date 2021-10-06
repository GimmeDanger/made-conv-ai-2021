theme: /
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_0
        state: 1
            intent: /sys/aimylogic/ru/hello

            go!: /newNode_1
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_0",
                name: "newNode_0 buttons",
                handler: function($context) {
                }
            });

    state: newNode_1
        a: Добрый день! Как вас зовут?
        go!: /newNode_2
    @IntentGroup
        {
          "boundsTo" : "/newNode_1",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_2
        state: 1
            intent: /sys/aimylogic/ru/name
            intent: /newNode_2/1

            go!: /newNode_12

        state: 2
            intent: /newNode_2/2

            go!: /newNode_10
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_2",
                name: "newNode_2 buttons",
                handler: function($context) {
                }
            });

    state: newNode_13
        a: Как вас зовут?
        go!: /newNode_14
    @IntentGroup
        {
          "boundsTo" : "/newNode_13",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_14
        state: 1
            intent: /sys/aimylogic/ru/name
            intent: /newNode_14/1

            go!: /newNode_12

        state: 2
            intent: /newNode_14/2

            go!: /newNode_10
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_14",
                name: "newNode_14 buttons",
                handler: function($context) {
                }
            });

    state: newNode_12
        if: $session.name = $session.NAME ? $session.NAME.name : $session.isname
            go!: /newNode_4

    state: newNode_10
        a: Вас точно зовут {{$session.isname}}?
        go!: /newNode_11
    @IntentGroup
        {
          "boundsTo" : "/newNode_10",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_11
        state: 1
            intent: /sys/aimylogic/ru/negation

            go!: /newNode_13

        state: 2
            intent: /sys/aimylogic/ru/agreement

            go!: /newNode_12
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_11",
                name: "newNode_11 buttons",
                handler: function($context) {
                }
            });

    state: newNode_4
        a: Приятно познакомиться, {{$session.name}}!
        go!: /newNode_9

    state: newNode_9
        EndSession:
