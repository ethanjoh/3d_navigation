# Virtual Reality Modeling Language (VRML) 가이드북
> 본 문서는 VRML의 개요, 개념, 역사, 설계 표준, 그리고 Java Applet을 활용한 **EAI(External Authoring Interface)** 구현 방법 및 **CosmoPlayer** 조작법을 정리한 개발용 기술 가이드북입니다.

---

## 1. VRML 개요 및 목적

### 1.1. 개념 (What is VRML?)
**VRML(Virtual Reality Modeling Language)**은 인터넷상에서 3차원 가상공간을 표현하기 위한 개방형이자 확장 가능한 장면 기술(Scene Description) 언어입니다. VRML을 사용하면 단순한 2차원 웹 문서 영역을 넘어 텍스트, 이미지, 애니메이션, 실시간 사운드가 결합된 입체적인 3차원 공간과 상호작용할 수 있습니다. 

* **VRML 1.0**: 단순 수동 항해 및 제한적인 개별 애니메이션만 지원
* **VRML 2.0 (ISO 표준)**: 복잡한 3D 가속 애니메이션, 시뮬레이션, 인터랙션 구조 및 JAVA/JavaScript 등 외부 프로그래밍 언어의 동적 제어를 완벽히 수용

### 1.2. VRML의 주된 사용 목적
* **가상 공간의 표상**: 인터넷, 인트라넷, 그리고 개인용 오프라인 환경 전체에서 3D 오브젝트와 씬(Scene) 정보 구축
* **포맷의 상호 운용성**: 3D 그래픽스 자원과 멀티미디어 미디어를 유기적으로 결합하는 표준 개방형 포맷 제공
* **다양한 산업 분야 활용**:
  - 공학 및 과학 데이터의 3차원 입체 시각화 (Scientific Visualization)
  - 상호작용 멀티미디어 프레젠테이션 (Interactive Presentation)
  - 교육, 시뮬레이션 및 오락 타이틀 제작
  - 차세대 3D 웹 홈페이지 및 메타버스 가상 가상세계 구현

---

## 2. VRML 설계 기준 및 특성

### 2.1. 설계 표준 기준 (Design Criteria)
1. **저작성 (Authoring)**: VRML 파일을 편집기나 전용 GUI 저작 도구로 직접 설계하고, 범용 3D CAD/CG 파일(3DS, DXF 등)을 손쉽게 VRML 규격으로 파싱 및 임포트 변환할 수 있는 호환성 보장
2. **구성성 (Composition)**: 가상 가상세계 내부에서 복잡하고 동적인 3D 물체를 모듈 단위로 선언하고 재사용할 수 있는 트리형 노드 구조 보장
3. **확장성 (Extensibility)**: 기본 사양에서 명시되지 않은 사용자 정의 복합 객체 유형(`PROTO`)의 독자 개발 및 배포 지원
4. **실행성 (Executability)**: 다양한 로우 레벨 그래픽스 장치 및 플랫폼 독립적인 웹 환경에서 실행 보장
5. **성능 최적화 (Performance)**: 이종의 네트워크 대역폭과 다른 하드웨어 성능 환경에서도 프레임 손실을 조절하고 실시간 사용자 인터랙션 속도 유지에 중점
6. **확장 모델 성능 (Scalability)**: 매우 복잡하고 크기가 거대한 대규모 3D 공간의 동적인 상호 연결 지원

### 2.2. 핵심 장치적 특성
* 정적/동적 성격의 3D 뽈리곤 오브젝트와 오디오/비디오 등 멀티미디어 소스 병합
* 임의의 가상 객체 노드에 타 미디어 및 다른 가상 세계 주소로 하이퍼링크 지정 기능
* 범용 시스템 플랫폼상에서 단일 표준 VRML 브라우저/뷰어를 기반으로 작동
* 기구축된 3D 그래픽 API(OpenGL, Direct3D 등)와의 원활한 기저 인터페이스 호환

---

## 3. JAVA와 VRML의 연동: EAI (External Authoring Interface)

### 3.1. EAI의 정의
**EAI(External Authoring Interface)**는 독립된 3차원 VRML 가상 세계와 외부 환경(웹 브라우저의 HTML 문서 및 Java 프로그램) 간의 데이터 흐름을 제공하는 표준 API 인터페이스입니다. HTML 페이지 내부에서 구동되는 **Java Applet**이 같은 도메인/페이지 영역에 렌더링되고 있는 VRML 씬 그래프(Scene Graph)의 내부 노드를 실시간 감시, 생성, 삭제, 이동 제어할 수 있습니다.

이를 통해 3차원 씬과 어우러지는 강력한 2차원 사용자 인터페이스(슬라이더, 폼, 버튼 등)를 결합할 수 있습니다.

```
[ HTML 브라우저 페이지 ]
┌──────────────────────────────────────────────┐
│  ┌───────────────────────┐  ┌─────────────┐  │
│  │   VRML 3D Viewport    │◀─│ Java Applet │  │
│  │ (e.g. CosmoPlayer 3D) │  │ (AWT/Swing) │  │
│  └───────────────────────┘  └─────────────┘  │
│              ▲                     │         │
│              └──────── EAI ────────┘         │
└──────────────────────────────────────────────┘
```

### 3.2. 실시간 개발 환경 설정 (준비물)
* **웹 브라우저**: Netscape Navigator 4.01 이상 혹은 Microsoft Internet Explorer 4.0 이상
* **3D 렌더러 엔진**: Cosmo Player 2.0 beta 1 이상 설치
* **자바 개발 킷**: JDK (Java Development Kit) 1.1+ 설치
* **자바 클래스 패스(CLASSPATH) 환경 변수 등록**: EAI 클래스가 임베디드된 라이브러리 파일(`Npcosmop.zip`)을 지정해야 합니다.
  ```bash
  # Windows 95 / NT 기반 설정 환경 예시
  set CLASSPATH=%CLASSPATH%;C:\Program Files\CosmoSoftware\CosmoPlayer\Npcosmop.zip
  ```

---

## 4. EAI 연동 및 제어 구현 방법

### 4.1. VRML 모델 구조 설계 (`TestModel.wrl`)
EAI를 통해 제어하고자 하는 루트 노드는 반드시 **`DEF`** 지시자를 활용해 고유 노드명을 정의해야 합니다. `DEF`문이 지정되지 않은 노드는 외부 자바 코드에서 탐색하거나 엑세스할 수 없습니다.

```wrl
#VRML V2.0 utf8

# 외부 자바 제어용 인터페이스의 Root 그룹 노드로 설정
DEF ROOT Transform {
    translation 0 0 0
    children [
        # 하위에 조작 및 렌더링될 내부 3D 노드 객체 배치
    ]
}
```

### 4.2. HTML 구조 바인딩 (`Test.html`)
동일 HTML 공간 내에 3D 뷰어와 제어용 자바 애플릿을 병렬 배치하고, 애플릿 내에서 VRML 조작용 스크립트 실행 권한(`mayscript`)을 부여합니다.

```html
<!-- VRML 3D 모델 씬 로딩 -->
<embed src="TestModel.wrl" width="500" height="400">

<!-- 제어용 자바 애플릿 탑재 (mayscript 옵션 필수) -->
<applet code="TestApplet.class" width="300" height="100" mayscript>
</applet>
```

### 4.3. Java Applet 소스코드 구현 (`TestApplet.java`)

#### A. EAI 클래스 라이브러리 임포트
```java
import java.applet.*;
import java.awt.*;

// EAI 연동을 위한 전용 패키지 클래스
import vrml.external.Browser;
import vrml.external.Node;
import vrml.external.field.*;
import vrml.external.exception.*;
```

#### B. 브라우저 세션 획득 및 노드 탐색
```java
// VRML 브라우저 인스턴스 참조 변수
Browser browser;
Node root;

public void init() {
    // 1. 동일 프레임/페이지 상에 존재하는 VRML 브라우저 엔진 객체 핸들 획득
    browser = Browser.getBrowser(this);

    // * 만약 타겟 뷰어가 별도 타겟 프레임에 로딩되어 있는 구주인 경우:
    // browser = Browser.getBrowser(this, "FrameName", 0);

    // 2. DEF로 정의된 루트 그룹 노드 핸들 조회
    root = browser.getNode("ROOT");
}
```

#### C. VRML 내부 객체 생성 및 동적 노드 삭제
자바 상에서 직접 스트링 포맷의 VRML 구문을 선언하여 이를 3D 씬 내에 노드 어레이로 빌드할 수 있으며, `addChildren`/`removeChildren` 이벤트를 송신하여 동적으로 처리합니다.

```java
Node[] shape;
EventInMFNode addChildren;
EventInMFNode removeChildren;

public void setupDynamicNodes() {
    try {
        // 루트 노드의 자식 제어 이벤트 포트 핸들 획득
        addChildren = (EventInMFNode) root.getEventIn("addChildren");
        removeChildren = (EventInMFNode) root.getEventIn("removeChildren");

        // 문자열 기반의 VRML 원시 코드를 노드 인스턴스 배열로 전환 파싱
        shape = browser.createVrmlFromString("Shape { geometry Box {} }");

        // 가상 세계 씬 그래프에 동적으로 추가
        addChildren.setValue(shape);
        
        // 가상 가상세계에서 특정 노드 그룹 제거 시
        // removeChildren.setValue(shape);
    } catch (InvalidEventInException ex) {
        ex.printStackTrace();
    }
}
```

#### D. VRML 모델 내부의 상태 조작 (이벤트 데이터 송신)
루트 노드 및 자식 기하 객체의 노드 값을 제어하기 위하여 물리 연동 파라미터를 이벤트 입력 필드(`EventIn`)를 통해 외부 송신합니다.

```java
// x, y, z 방향의 좌표 오프셋 데이터 변수
float[] xyz = new float[]{1.5f, 2.0f, -0.5f};
EventInSFVec3f translation;

public void updateModelPosition() {
    try {
        // 대상 Root 노드의 set_translation 필드 호출 인터페이스 획득
        translation = (EventInSFVec3f) root.getEventIn("set_translation");
        
        // 새로운 타겟 좌표 배열 값 적용
        translation.setValue(xyz);
    } catch (InvalidEventInException ex) {
        ex.printStackTrace();
    }
}
```

#### E. VRML 모델 내부의 상태 변화 감지 (이벤트 수신 및 Callback 처리)
가상현실 내부의 사용자 상호작용 또는 움직임 데이터를 수신하기 위해 자바 측에서 `EventOutObserver` 감시 인터페이스를 연결합니다.

```java
public class TestApplet extends Applet implements EventOutObserver {
    EventOutSFVec3f outTranslation;

    public void startEventListening() {
        try {
            // 위치 변환에 대응되는 EventOut 필드 핸들 획득
            outTranslation = (EventOutSFVec3f) root.getEventOut("translation");
            
            // 본 클래스를 이벤트 옵저버 핸들러로 연동 등록
            outTranslation.advise(this, new Integer(1));
        } catch (InvalidEventOutException ex) {
            ex.printStackTrace();
        }
    }

    // VRML 내부 필드 데이터가 변형될 때 호출되는 이벤트 가로채기 콜백 메소드
    public void callback(EventOut who, double when, Object which) {
        // 변경되어 전달된 실시간 좌표값 조회
        float[] val = outTranslation.getValue();
        System.out.println("Changed Position: X=" + val[0] + ", Y=" + val[1] + ", Z=" + val[2]);
    }
}
```

---

## 5. 역사 및 발전 방향 표준 동향

### 5.1. VRML의 역사적 태동
1994년 5월, 스위스 제네바에서 개최된 제1회 국제 World Wide Web 컨퍼런스에서 웹상에 3D 입체 영역을 실시간 가시화하기 위한 표준 규약에 대해 의논하기 시작했습니다. 

* **핵심 발기인**: Tim Berners-Lee(HTML 설계자), Dave Ragget(HP), Mark Pesce(VRML 리스트 메인 관리자), Gavin Bell(오픈 인벤터 개발자) 등 그래픽스 및 네트워크 표준 주도 집단 참여
* **명칭 정립**: 본래 'Virtual Reality Markup Language'로 태동하였으나, 고도의 입체 그래픽스 기술 성격을 직관적으로 묘사하고자 'Virtual Reality **Modeling** Language'로 개칭
* **포맷의 토대**: Silicon Graphics(SGI)사가 보유한 유닉스 기반 3D 프레임워크 언어 **'오픈 인벤터(Open Inventor)'**의 3차원 자료 구조 명세를 기초로 데이터 포맷 슬림화를 추진하여 **VRML 1.0 표준 규격** 최종 비준 및 최초의 표준 뷰어 **'WebSpace'**를 보급

### 5.2. VRML 2.0 표준 기술의 고도화 (Moving World)
초기의 VRML 1.0은 좁은 단일 전송선로(당시 28.8Kbps 모델 중심 하부 인프라) 환경에서 용량이 방대한 3D 폴리곤 데이터를 다운로드하기 어렵다는 전송 데이터 용량 장벽이 지적되었습니다. 또한, 정지된 정적 그래픽 모델 감상용에 한계가 뚜렷했습니다.

이를 위해 실리콘 그래픽스와 Netscape사가 제안한 **"Moving World(생동하는 세계)"** 표준 명세안이 Microsoft가 자체 프로모션하던 "Active VRML" 세력을 넘고 **VRML 2.0 국제 공식 가상현실 표준**으로 탑재되었습니다.

* **동적 멀티미디어 통합**: RealAudio 사운드 전송 프로토콜, 동화상 압축 스트리밍, 쇼크웨이브 등 웹 보조 미디어 도구들이 그대로 VRML 노드로 통합됨
* **프로그래밍 구조 결합**: 자바(Java VM) 및 자바스크립트가 VRML 코어와 접목되면서 3D 기하 장면에 능동형 라이프사이클을 인가
* **가상 가상사회 인프라 확장**: Worlds사 등의 '알파월드(AlphaWorld)' 가상 대규모 커뮤니티 개발로 메타버스의 전신이 되는 3D 네트워크 상호교류 기술 구현

---

## 6. VRML 핵심 용어 가이드 (Terminology)

* **Appearance node**
  재질(`Material`), 텍스처 맵(`ImageTexture`, `MovieTexture`, `PixelTexture`), 서체 규격(`FontStyle`), 텍스처 변환 매트릭스(`TextureTransform`) 등을 하나로 구조화하여 3D 표면 그래픽 재질 스타일을 선언하고 제어하는 지휘용 노드군.
* **Bindable node**
  `Background`(가상 배경), `Fog`(안개 효과), `NavigationInfo`(조작 규칙), `Viewpoint`(카메라 위치 정보) 노드를 의미합니다. 장면에 복수의 바인딩 가능 노드가 위치하더라도 특정 시점(Active)에서는 단 하나의 바인딩 인스턴스만 활성 렌더링됩니다.
* **Children node**
  `Group` 또는 `Transform`과 같은 상위 그루핑 노드의 산하 하위 계층에 포지셔닝되어, 부모 및 조상 노드로부터 오는 이동, 회전, 스케일링 등 모든 기하 공간 행렬 변형 정보에 직접적인 상속 영향을 받는 개별 하부 기하 노드들.
* **Drag sensor**
  `CylinderSensor`, `PlaneSensor`, `SphereSensor` 노드군. 포인터(마우스)의 물리 궤적 입력 행동에 기반하여 회전각, 2D 평면 위치값, 가상 구형 변위 등의 회전/이동 이벤트 출력을 산출합니다.
* **Event & Route**
  가상공간 내부에서 노드 간의 메시지를 실시간으로 릴레이하기 위한 논리 통로입니다. `ROUTE node1.eventOut TO node2.eventIn` 구조로 연결되며, 이벤트 신호를 실시간 처리함으로써 모델들의 상태를 런타임에 동적으로 동기화합니다.
* **exposedField**
  내부 필드 데이터 변화를 감지하여 외부 또는 외부 라우트로 동등 이벤트를 실시간으로 동시 송수신할 수 있는 양방향 액세스 속성 필드.
* **Geometry node**
  `Box`, `Cone`, `Cylinder`, `ElevationGrid`(격자형 단면), `Extrusion`(단면 압출), `IndexedFaceSet`(수동 선언 폴리곤), `IndexedLineSet`(입체 선), `PointSet`(점 집합), `Sphere`, `Text` 등 수학적 공식으로 3차원 외형 실체를 규정하는 기본 도형 리스트.
* **Grouping node**
  `Anchor`(하이퍼링크 그룹), `Billboard`(사용자 화면 정면 직면 그룹), `Collision`(충돌 메쉬 연산 제어 그룹), `Group`, `Transform` 등 계층형 공간 그래프 조립 및 연동에 가용되는 그룹 지시자.
* **Instance**
  장면 그래프 내에 동일한 노드를 중복 선언하여 데이터 오버헤드가 발생하는 것을 억제하기 위한 키워드(`DEF`/`USE`). `DEF`로 단일 원본 노드 정의 시 고유 ID명을 명기하고, 이를 타 위치에 복제 복사할 때 `USE` 구문으로 호출하여 참조 메모리를 크게 절감합니다.

---

## 7. CosmoPlayer 조작 가이드

**CosmoPlayer**는 Silicon Graphics(SGI)사에서 자바스크립트 내장 제어 및 가벼운 저작 엔진 기능을 탑재하여 제작한 공식 VRML 2.0 3D 뷰어 엔진 플러그인입니다.

### 7.1. 3D 씬 내 탐색 및 비행 조작 모드 (Navigation Modes)
사용자는 마우스를 클릭 드래그하여 가상 환경 안에서 기하 기기를 둘러보고, 다양한 상호작용적 조작 모드를 전환하여 여행할 수 있습니다.

* **Walk (걷기 모드)**: 
  바닥 평면 구조를 기준으로 지상 보행하듯 자연스러운 탐색을 시도하는 모드. 마우스의 왼쪽 버튼을 유지한 상황에서 마우스를 정면 앞방향으로 밀면 **전진**, 마우스 드로잉을 아래로 끌어당기면 **후진**, 좌우 횡이동에 맞춰 **좌/우 선회**를 유연하게 지원하며 드래그 거리 및 시선 앵글 오프셋 폭에 의해 전진 가속 속도가 산정됩니다.
* **Slide (미끄러지기 모드)**: 
  카메라 렌즈의 응시 포인트를 전방에 수평 고정 유지한 상태로 화면 기준 상하좌우 평행 이동(Panning)만을 전담하여 경로 방해물을 민첩하게 회피하거나 기하 기기의 정면 좌우면 단면 분석 시 용이한 조작 모드.
* **Look (보기 모드)**: 
  관람자의 물리적 이동 앵글을 가상 공간에 임시 고정(Lock) 처리한 상태에서, 관조자의 시선 헤더만을 상하좌우 시선 정렬 회전(Tilt/Pan)시키는 기능. 고정 관찰 상태에서 상단 구조물 뷰 또는 바닥면 기하 모델 조망 시 유용.
* **Examine (관찰 모드)**: 
  관심 있는 특정 3D 객체 노드를 중심 축으로 씬 월드 전체의 물리 공간을 마우스 트랙볼 구조처럼 이리저리 다각도 공중 회전시키며 정밀 형상을 입체적으로 다방면 관측하는 인터랙티브 가속 뷰 포트 모드.

### 7.2. 원격 길 잃음 방지 (View 기능)
3차원 항해 중 화면 오동작이나 과도한 드래그 오프셋 오류로 카메라가 가상 가상세계 영역을 이탈하여 암전 영역에서 길을 잃었을 경우, 컨트롤 툴바에 부착된 **`View`** 홈 포지션 버튼을 입력하면 즉시 저작자가 가상공간 내에 최초 정의해둔 복구용 **기본 스타트 카메라 Viewpoint 위치**로 최단 경로 자동 비행하여 되돌아갈 수 있습니다.
