import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/pages/dialogue/dialogue_controller.dart';
import 'package:pot_chat_front/routes/app_pages.dart';

class DialogueBody extends GetView<DialogueController> {
  DialogueBody({super.key});

  final String sessionId = Get.parameters['sessionId'] as String;
  final String description = (Get.parameters['description'] ?? '新建聊天');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text(description),
      ),
      body: Center(),
    );
  }
}
