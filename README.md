*Jakub Budzyński - 247015*

# HERO APP - catalog app
### Android min sdk - *24*
### Android target version - *30*
### Made on the device - ~~Pixel 3A~~

> Aplikacja potrzebuje dostępu do internetu, pliki konfiguracji są w Kolinie a nie w Groovy

Aplikacja pozwala na przeglądanie katalogu seperbohaterów pobieranych z serwera ***GraphQL***.  Element ekranu listy składa się z obrazka, nazwy superbohatera i jego kategorii oraz przycisku dodania do ulubionych. Po kliknięciu przechodzi się do ekranu szczegółów. Składa się z trzech slajdów: opisu, galerii i listy postaci występujących razem z daną postacią w komiksach.
<br/>
Dane pobierane są z graphql endpointu generowanego przez **GraphCMS**. Pozwoliło mi to w łatwy sposób zarządzać *danymi w aplikacji, relacjami postaci i grafikami*. Jest on publicznie otwarty ale jeżeli nie pojawiają się dane na początku to jest to prawdopodobnie spowodowane właśnie przez to. Początkowe ładowanie danych ukrywane jest przez kilkusekundowy **SplashScreen**.
Listę można sortować po dostępnych kategoriach, wyświetlać tylko ***ulubione*** postacie, lub wrócić do domyślnego sortowania. <br/>Opis postaci wyświetlany jest jako *html/RichText*. Do wyświetlania zdjęć używam biblioteki **coil**, która cachuje zdjęcia. Kilka innych wymaganych *bibliotek/pakietów*:

 - Coil
 - Gson
 - Material UI
 - CardView
 - Apollo GraphQL client
 - Navigation Fragment

<br/>
<img width="958" alt="Screenshot_3" src="https://user-images.githubusercontent.com/72972091/101269093-1481a080-376b-11eb-8b21-8c970bc527f4.png">
<img width="398" alt="Screenshot_1" src="https://user-images.githubusercontent.com/72972091/101268943-5d385a00-3769-11eb-8d54-e989800329ed.png">
<img width="392" alt="Screenshot_2" src="https://user-images.githubusercontent.com/72972091/101268980-a1c3f580-3769-11eb-8fb3-772bf472c726.png">
<img width="396" alt="Screenshot_1" src="https://user-images.githubusercontent.com/72972091/101269000-d5068480-3769-11eb-853c-994809e21861.png">
<img width="401" alt="Screenshot_3" src="https://user-images.githubusercontent.com/72972091/101269006-eea7cc00-3769-11eb-906c-90ce86657284.png">
<img width="395" alt="Screenshot_4" src="https://user-images.githubusercontent.com/72972091/101269016-0e3ef480-376a-11eb-8df8-7a9631b10888.png">
<img width="399" alt="Screenshot_1" src="https://user-images.githubusercontent.com/72972091/101269022-1dbe3d80-376a-11eb-95ca-dbbb99969688.png">
<img width="393" alt="Screenshot_2" src="https://user-images.githubusercontent.com/72972091/101269034-43e3dd80-376a-11eb-8130-7cbfc704d994.png">
<img width="391" alt="Screenshot_1" src="https://user-images.githubusercontent.com/72972091/101269041-68d85080-376a-11eb-8d41-bb5c4ea5b14f.png">
