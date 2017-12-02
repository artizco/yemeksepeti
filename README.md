# Yemeksepeti Assignment

Proje 3 sayfadan olusmaktadir:Login, Users, Profile

Api baglantisi olmadigi icin servis isteklerine cevap olarak assets dosyasindaki mock json'lar kullanilmistir.

Login 
Kullanici adi ve sifre girilerek uygulamaya giris yapilir. Kullanici adi minimum 3 karakter sifre ise minimum 4 karakter olmalidir. Login isleminin basarili olmasi durumunda users sayfasi acilir ve login sayfasi stackten silinir. 

Users
Kullanicilarin resimleri ve isimleri bu sayfada listelenir. Kullanici kartlarindan birine tiklanildiginda Profile sayfasina acilir. 

Profile 
Kullanicinin temel bilgileri bu sayfada gosterilir. Kullanici sadece adres ve telefon numarasini duzenleyebilir. 
NOT: Kullanici id'si ile sorgu atilmaktadir ancak response mock oldugu icin tum sorgularda ayni cevabi donmektedir. Tum kullanicilarin profillerinde ayni bilgiler goruntulenmektedir. 


Instrumentation Test

Happy path ve error path olmak uzere iki farkli sekilde uygulamanin tum akisini test eder.


Unit Test

Presenter class'larinin unit testleri yazilmistir. 

Kullanilan Kutuphaneler
  
  Kotlin
  Gson 
  Retrofit
  Picasso
  Espresso
  Mockito
  Junit
  Logger

