# Домашнее задание №3: Создание навыка для Маруси

### Что нужно сделать:
* воспроизвести функционал навыка* из урока в любом из инструментов (Aimylogic, JAICP, Dialogflow и т.д.) 
* добавить интент “кто тебя сделал”, возвращающий ваши ФИО
* результат выполнения ДЗ: прислать хук для тестирования навыка в отладчике Маруси (https://skill-debugger.marusia.mail.ru/)
* навык должен извлекать из пользовательского запроса название страны и с помощью рассмотренного на уроке сервиса (https://query.wikidata.org) возвращать имя президента.

### Тестовый сценарий для проверки (страны могут отличаться):

<pre>
    Кто президент России?
    А Франции?
    Кто тебя сделал?
</pre>

### Схема диалогов бота (Aimylogic)

> ![](bot-flow.png)

### Отладка Маруси

> ![](marusia-debug.png)
