import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/models/user.dart';
import 'package:pot_chat_front/pages/session/session_controller.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:sp_util/sp_util.dart';

class PromptBody extends GetView<SessionController> {
  PromptBody({super.key, required this.content});

  final Map<int, String> content;

  final selectedOption = 0.obs;

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text('请选择对话的角色'),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          for (var option in content.entries)
            Obx(() => RadioListTile(
                  title: Text(option.value),
                  value: option.key,
                  groupValue: selectedOption.value,
                  onChanged: (value) => {selectedOption.value = value!},
                )),
        ],
      ),
      actions: [
        ElevatedButton(
          child: const Text('确认'),
          onPressed: () {
            controller.create(selectedOption.value).then((value) {
              if (value != null && value.isNotEmpty) {
                Get.toNamed(
                    "${AppRoutes.dialogue}?sessionId=$value&description=新建聊天");
              }
            });
          },
        ),
        ElevatedButton(
          child: const Text('关闭'),
          onPressed: () {
            Get.back();
          },
        ),
      ],
    );
  }
}
