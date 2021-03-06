# Риболовни Дневници

Смяна на езика: [Английски](README.md),[Български](README.bg.md)

### Преглед.    
Това е проект създаден като дипломна разработка. Уеб приложението дав аинформация на потребителите за рибните видове живеещи във всички водоеми намиращи се на територията на България. Регистрираните потребители могат да създават собствени риболовни дневници, съдържащи информация за уловената от тях риба, използваната стръв и водоема в който е направен улова. Приложението разполага и с интерактивна карта на България на която могат да бъдат открити всички български водоеми и при кликане върху маркерите им се достъпва допълнителна информация за тях.

### Използвани инструменти и софтуер.  

Среда за разработка- Уеб приложението е създадено в средата за разработка Eclipse версия 2021-06  
Средство за автоматизиране на проекта - Maven.  
Backend (или “server-side”) - Spring Boot версия 2.5.4.  
Frontend (или “client-side”) - Изгледа на уеб приложението е постигнат чрез комбинация от: Thymeleaf, HTML, CSS, Javascript, Bootstrap, JQuery.  
HTTP заявки - Заявките от клиента към сървъра стават чрез използването на  Асинхронни JavaScript и XML или Ajax.  
Защита - За защита на приложението се използва Spring Security , която дава достъп до системата само на потребители със съответните права.  
Вграден сървър - Apache Tomcat.  
Система БД - MySQL.  


### Инсталация.
Клонирайте проекта и го импортнете в желаната от вас среда за разработка.

### Стартиране.
При работа с Eclipse е необходимо да се добави "Spring Tools" от маркета за да може eclipse да разпознава проекта като spring boot приложение.


### Изглед на уеб приложението

Страница за вход в системата:
![image](https://user-images.githubusercontent.com/52286225/136865273-5b154ded-5cba-43e2-9a25-53c6a2e3310d.png)


Интерактивна карта и странично меню:
![image](https://user-images.githubusercontent.com/52286225/136865375-eb2abb30-de09-42d5-a226-51d81234f561.png)


Страница с информация за вид риба:
![image](https://user-images.githubusercontent.com/52286225/136865599-32724bb2-dd9a-4905-8fb1-d353416d09bf.png)


Страница за предлагане на нов вид риба:
![image](https://user-images.githubusercontent.com/52286225/136865756-198f081f-e7f1-4b28-a2e4-0173fe7e2655.png)
