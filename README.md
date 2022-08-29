# SharingMyWishList

## 개요
2022 1학년 밀리그램들의 프로젝트 '공유 위시리스트'의 안드로이드 레포지토리 입니다!

- 개발 기간 : 2022.08 ~

## 개발 정보
- 개발 도구
  - Android Studio
  - Postman
- 개발 언어
  - Java
- 개발 기술
  - Retrofit
  - DataBinding

## Developers

- Android : 박준수

> ### Developers working on other sides
> - Server : 길근우
> - iOS : 박주영, 조병진
> - Design : 박준수

## Retrospect
- RecyclerView에서도 ViewBinding을 사용할 수 있다는 사실을 뒤늦게 꺠달아서 RecyclerView Adapter를 작성할 때 일일히 findViewById() 메소드를 호출하는 작업을 거쳤다.
- createWish(), getAllWish() 등의 여러 클래스에서 사용되는 메서드는 하나의 클래스에서 관리하고, 그 클래스의 인스턴스를 생성하여 다른 클래스에서 호출하는 방법을 사용했더라면 관련 문제에 직면했을 때 유연하게 클래스 멤버 함수를 호출하는 방법으로 대처할 수 있었을 것이다.
