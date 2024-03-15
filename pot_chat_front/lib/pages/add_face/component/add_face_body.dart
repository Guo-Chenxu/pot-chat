import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/models/user.dart';
import 'package:pot_chat_front/pages/add_face/add_face_controller.dart';
import 'package:pot_chat_front/pages/add_face/component/verify_code_body.dart';
import 'package:sp_util/sp_util.dart';

class AddFaceBody extends GetView<AddFaceController> {
  AddFaceBody({super.key});

  User u = User.fromJson(Map.from(SpUtil.getObject(Constants.user)!));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("添加人脸"),
      ),
      body: Center(
        child: ListView(
          children: [
            Center(
              child: Text("正在向 ${u.email} 发送验证码"),
            ),
            VerifyCodeBody(email: u.email!),
            const SizedBox(height: 20),
            Center(
              widthFactor: 1,
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(backgroundColor: Colors.blue),
                onPressed: () => {controller.addFace()},
                child: const Text(
                  "添加人脸",
                  style: TextStyle(color: Colors.white),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
