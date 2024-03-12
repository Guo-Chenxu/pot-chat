import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/register/register_controller.dart';

// 头像
class AvatarBody extends GetView<RegisterController> {
  AvatarBody({super.key});

  RxString avatar = "".obs;

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Obx(
          () => GestureDetector(
            child: Container(
              alignment: Alignment.center,
              width: 70,
              height: 70,
              child: avatar.value.isNotEmpty
                  ? CircleAvatar(
                      radius: 50,
                      backgroundImage: NetworkImage(avatar.value),
                    )
                  : const CircleAvatar(radius: 50),
            ),
            onTap: () {
              controller.uploadAvatar().then((value) {
                avatar.value = value;
                controller.onAvatarChanged(value);
              });
            },
          ),
        ),
      ],
    );
  }
}
