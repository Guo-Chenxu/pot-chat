import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/register/register_controller.dart';

// 昵称框
class NicknameBody extends GetView<RegisterController> {
  const NicknameBody({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      child: TextField(
        autofocus: true,
        onChanged: (value) => controller.onNicknameChanged(value),
        decoration: const InputDecoration(
          labelText: '昵称',
          hintText: '给自己取一个好听的名字吧',
          prefixIcon: Icon(Icons.person),
        ),
      ),
    );
  }
}
