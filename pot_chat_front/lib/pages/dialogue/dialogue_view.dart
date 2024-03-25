import 'package:flutter/material.dart';

import 'package:get/get.dart';
import 'package:pot_chat_front/pages/dialogue/component/dialogue_body.dart';
import 'package:pot_chat_front/pages/dialogue/dialogue_controller.dart';

class DialogueView extends StatelessWidget {
  const DialogueView({super.key});

  @override
  Widget build(BuildContext context) {
    return GetBuilder<DialogueController>(
      builder: (controller) => Scaffold(
        body: DialogueBody(),
      ),
    );
  }
}
