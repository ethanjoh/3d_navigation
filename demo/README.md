<!-- 3D GLB 뷰어 애플리케이션의 소개 및 로컬 실행 방법을 가이드하는 리드미 문서 -->
# 3D GLB Model Viewer (Three.js)

이 프로젝트는 웹 브라우저에서 별도의 확장 프로그램(Plugin) 설치 없이 Three.js 엔진을 기반으로 다양한 3D GLB 입체 모델을 실시간 로딩하여 감상할 수 있는 데모 애플리케이션입니다.

기존의 WRL(VRML) 파일을 3D 그래픽 도구인 **Blender에서 Import한 후 GLB 포맷으로 변환**하여 Three.js 환경에서 원활히 구동할 수 있도록 최적화되었습니다.  

그래픽 디자이너가 아닌 관계로 모델링이나 렌더링이 예쁘지 않은 점 양해바랍니다.
추후 조작이 가능하도록 업데이트 될 예정입니다.  

* **데모:** [https://ethanjoh.github.io/3d_navigation/](https://ethanjoh.github.io/3d_navigation/)

---

## 🚀 로컬 실행 방법 (CORS 우회)

3D 모델 파일(`.glb`)을 브라우저에 비동기 로드할 때 보안 정책(CORS)에 저촉되지 않도록, 단순히 html 파일을 더블 클릭하여 실행하는 대신 **로컬 웹 서버**를 가동해야 합니다.

아래 방법 중 본인의 환경에 맞는 한 가지 방법을 골라 실행해 주세요.

### 방법 A: Python이 설치된 경우 (가장 간단)

1. 터미널(CMD 또는 PowerShell)을 열고 현재 `demo` 폴더로 이동합니다.
2. 아래 명령어를 실행하여 웹 서버를 엽니다:

   ```bash
   python -m http.server 8000
   ```

3. 브라우저 주소창에 [http://localhost:8000](http://localhost:8000)을 입력하여 접속합니다.

### 방법 B: Node.js (npm)가 설치된 경우

1. 터미널을 열고 현재 `demo` 폴더로 이동합니다.
2. 아래 명령어를 실행하여 즉석 서버를 가동합니다:

   ```bash
   npx http-server -p 8000
   ```

3. 브라우저 주소창에 [http://localhost:8000](http://localhost:8000)을 입력하여 접속합니다.

### 방법 C: VS Code(비주얼 스튜디오 코드)를 사용 중인 경우

1. VS Code 마켓플레이스에서 **Live Server** 확장 프로그램(Extension)을 설치합니다.
2. `demo` 폴더를 열고 화면 우측 하단의 **Go Live** 버튼을 누르거나, `index.html` 파일을 우클릭하여 **Open with Live Server**를 선택합니다.

---

## 🕹️ 3D 화면 조작 안내

마우스를 사용하여 뷰포트 내의 3D 모델을 다각도로 감상할 수 있습니다.

- 🖱️ **마우스 좌클릭 드래그**: 카메라 회전 (Orbit Rotate)
- 🖱️ **마우스 우클릭 드래그**: 화면 시점 이동 (Pan)
- 🎡 **마우스 휠 스크롤**: 화면 확대 및 축소 (Zoom In/Out)
