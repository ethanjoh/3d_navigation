<!-- Git 저장소 동기화 및 변경 이력을 기록하는 문서 -->
# CHANGELOG

## [2026-07-14]
### 변경 목적
* 로컬 디렉토리와 GitHub 원격 저장소(`https://github.com/ethanjoh/3d_navigation`)의 동기화 설정 및 동기화 파일 범위 제한

### 주요 결정 사항
* 원격 저장소의 `README.md`와 `images/` 폴더만 동기화하도록 결정.
* 로컬에 있는 다른 논문 원본 파일(`paper.md`, `paper.hwp` 등)은 원격에 공유되지 않도록 `.gitignore` 설정을 적용하여 Git 추적에서 제외함.

### 수정한 파일
* [NEW] `.gitignore`: `README.md`와 `images/` 폴더만 허용하고 그 외 모든 파일을 무시하는 규칙 추가
* [NEW] `CHANGELOG.md`: 변경 기록 파일 생성

### 테스트 결과
* `git status` 명령을 통해 `.gitignore`가 올바르게 작동하여 `README.md` 및 `images/` 외의 파일들이 모두 추적 대상에서 배제된 것을 확인함.

---

## [2026-07-14 - 추가 작업]
### 변경 목적
* `README.md` 내에 VRML 적색 원뿔(Cone) 실행 예시 이미지 링크 추가

### 주요 결정 사항
* VRML Cone 예시 코드 설명 하단에 `images/paper_image1.png` 이미지가 표시되도록 마크다운 이미지 구문 추가

### 수정한 파일
* [MODIFY] [README.md](file:///e:/개인_백업/Backup/WORK/논문/README.md)

### 테스트 결과
* 마크다운 내 이미지 상대 경로(`images/paper_image1.png`)의 올바른 연결 확인.

