# BottomSheet 연습

- [Bosic](./documents/Basic.md) : 공식 문서를 보고 학습
- TorangBottomSheet : bottom sheet 기능을 구현하며 정리하기 위해 만든 bottom sheet의 custom 위젯
- [FixedInputBottomSheetScaffold](./documents/FixedInputBottomSheetScaffold.md) : 하단에 고정 위치 입력창 설정

## [FixedInputBottomSheetScaffold](./documents/FixedInputBottomSheetScaffold.md)

<img src = "./screenshot/fixed_input_bottom_sheet_scaffold.gif" width="300" />

ModalBottomSheet를 사용하려다 실패함.(모달 화면의 위에 추가적인 UI 레이어를 올릴 수 없었음.)
BottomSheetScaffold로 구현. (BottomSheet위에 다른 UI 레이어를 올릴 수 있음.)

### 안드로이드 스튜디오 Preview Interactive mode 사용시 주의

Preview의 Interactive mode에서 modal이 뜰 때 배경이 사라지는 오류가 발생
실제로 기기에서 동작 시킬때는 뒤에 배경이 나옴 (ModalBottomSheet의 배경에 내용을 넣는 파라미터가 있는지 한참 찾았음ㅠ)
