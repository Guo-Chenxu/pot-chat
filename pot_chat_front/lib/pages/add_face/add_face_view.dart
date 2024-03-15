import 'package:flutter/material.dart';

import 'package:get/get.dart';
import 'package:pot_chat_front/pages/add_face/add_face_controller.dart';
import 'package:pot_chat_front/pages/add_face/component/add_face_body.dart';

class AddFaceView extends StatelessWidget {
  const AddFaceView({super.key});

  @override
  Widget build(BuildContext context) {
    return GetBuilder<AddFaceController>(
      builder: (controller) => Scaffold(
        body: AddFaceBody(),
      ),
    );
  }
}
