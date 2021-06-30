# 산갈래 : 등산 측정 및 등산로 추천 어플리케이션
- 개요
 1. 주제 선정 배경  
COVID-19로 인해 실내 운동이 제한됨에 따라 도심과 인접한 산을 찾는 등산객들의 수가
증가하고 있다. 초보 등산객들의 경우, 길을 잘못 들거나 등산로의 난이도를 잘 모르고
가벼운 차림으로 등산을 하다가 사고가 발생하는 경우가 많다. 또한 자신에게 맞는 수준의
등산로 정보를 구하기가 어렵다. 본 프로그램은 유저들에게 맞춤형으로 등산로를 추천하고
본인의 등산 데이터를 관리할 수 있는 서비스를 제공해주는 어플리케이션을 개발해 보다 더
안전하고 즐겁게 등산을 즐길 수 있도록 주제를 선정했다.
 2. 목적  
등산로 추천 및 측정 어플리케이션으로 유저의 수준에 적합한 난이도의 등산로를
추천해주고, 산행 중에 길을 잃지 않도록 GPS 정보를 통해 경로를 안내한다. 원하는 길이와
수준의 경로를 검색할 수도 있고, 유저의 등산 데이터를 수집해 유저의 취향에 맞는
등산로를 추천해 준다.

- 기능
  1. 주요 기능  
① 등산로/산 추천  
유저의 등산 기록과 등산로들의 메타데이터를 분석해 유저의 수준에 적합하고 취향에
맞는 등산로를 추천한다. 또한, 최근 24개월 이내에 따라가기 횟수가 많은 인기 등산로,
현재 계절의 순위를 반영한 인기 산 목록, 현재 유저의 위치로 부터 50km 이내에 있는 근처
산 목록을 제공한다.  
② 키워드 검색  
등산로명, 산 이름, 지역 명 등의 키워드로 검색을 하면 해당 키워드가 포함된 등산로
목록을 보여준다.  
③ 등산로 안내 및 측정  
등산로 상세 페이지에서 따라가기 버튼을 클릭하면, 해당 등산로의 경로를 지도에
표시한다. 안내 시작 버튼을 클릭하면 측정 시간, 걸은 시간, 이동한 거리, 남은 거리, 현재
고도, 예상 도착 시간, 진행률을 화면에 표시한다. 3초 간격으로 현재 유저가 위치한 곳의
위도, 경도, 고도와 시간을 기록한다. 일시정지 버튼을 클릭하면 등산 안내 및 측정을
일시적으로 중단할 수 있다. 등산 측정이 끝나면 해당 등산 데이터는 GPX 파일로 생성되고,
로컬 저장소 및 서버 스토리지에 저장되어 유저가 언제든지 본인의 등산 기록을 확인할 수
있다.  
④ 마이페이지, 등산 통계  
닉네임, 키, 몸무게 등 유저의 프로필을 직접 수정할 수 있고, 유저의 등산 통계 및 등산
기록 목록을 확인할 수 있다. 등산 통계 화면에서는 등산 거리, 등산 시간, 이동 시간,
오르막합, 내리막합, 속력, 페이스 등의 합계, 평균, 최고 기록을 제공하여, 유저의 등산
성향을 파악할 수 있다.  
  2. 부가 기능

- 설계
- 파일 설명
