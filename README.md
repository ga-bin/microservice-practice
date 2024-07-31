# microservice-practice
* udemy의 Master Microservices with SpringBoot,Docker,Kubernetes강의를 듣고 작성한 코드입니다.

## section2
* jpa를 이용한 microservices 생성

## section4
* docker file, buildpacks, google jib을 활용한 docker 이미지 생성 및 컨테이너 관리

## section6
* 컨테이너 내에서 필요한 설정정보를 관리하기 위해 config 서비스 분리
* spring cloud를 활용한 설정정보 서비스 내 자동 주입
  - classpath, file, github를 통한 설정 정보 경로 설정
  - 암호화, 복호화
* actuator를 이용해 서버 재기동 없이 설정 정보 refresh
* rabbitMQ를 이용해 모든 컨테이너 한 번에 설정정보 refresh
* actuator + docker를 이용해 컨테이너의 liveness, readiness 관리
* docker-compose를 통해 컨테이너 들의 실행 순서 및 configserver에서 주입할 설정정보, healthy 등 관리
  - 각 서비스들의 image 생성
  - 생성된 이미지들에 docker-compose에 설정된 설정 파일 주입
  - docker 컨테이너 생성 시 docker-compose의 설정에 따라 컨테이너들 간의 실행 순서 및 configserver에서 주입할 설정정보, healthy 등 관리
  
## section7
* 각 microservice별 mysql 컨테이너 생성 후 연동
* docker network 동작 방식 이해

## section8
* microservices에서 생성되는 여러 컨테이너들의 health를 확인하고 클라이언트 측 로드밸런싱 하기 위한 spring cloud neflix 실습
  - eureka를 이용한 microservice들 recovery discovery
  - 
