import 'package:flutter/material.dart';
import 'package:get/get_state_manager/src/simple/get_view.dart';
import 'package:pot_chat_front/pages/dialogue/dialogue_controller.dart';

class InputBody extends GetView<DialogueController> {
  InputBody({super.key, required this.sessionId});

  final TextEditingController _textEditingController = TextEditingController();

  String sessionId;

  @override
  Widget build(BuildContext context) {
    return Align(
      alignment: Alignment.bottomCenter,
      child: Container(
        margin: const EdgeInsets.all(8.0),
        decoration: BoxDecoration(
          color: Colors.grey[200],
          borderRadius: BorderRadius.circular(25.0),
        ),
        child: Container(
          decoration: BoxDecoration(
            border: Border.all(color: Colors.blue),
            borderRadius: BorderRadius.circular(24.0),
          ),
          padding: const EdgeInsets.symmetric(horizontal: 8.0),
          child: Row(
            children: [
              Expanded(
                child: TextField(
                  autofocus: true,
                  controller: _textEditingController,
                  // onChanged: (value) => controller.onContentChanged(value),
                  decoration: const InputDecoration(
                    hintText: '和PotChat聊天',
                    border: InputBorder.none,
                    contentPadding:
                        EdgeInsets.symmetric(horizontal: 16.0, vertical: 12.0),
                  ),
                ),
              ),
              IconButton(
                icon: const Icon(
                  Icons.send,
                  color: Colors.blue,
                ),
                onPressed: () {
                  if (_textEditingController.text.isEmpty) return;
                  controller.send(
                    sessionId,
                    _textEditingController.text,
                  );
                  _textEditingController.clear();
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
