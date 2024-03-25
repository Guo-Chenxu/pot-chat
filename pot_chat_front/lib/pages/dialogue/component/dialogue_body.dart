import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/dialogue/component/history.dart';
import 'package:pot_chat_front/pages/dialogue/component/input_body.dart';
import 'package:pot_chat_front/pages/dialogue/dialogue_controller.dart';

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
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Expanded(
            child: HistoryBody(sessionId: sessionId),
          ),
          InputBody(
            sessionId: sessionId,
          ),
          const SizedBox(height: 10),
        ],
      ),
    );
  }
}
