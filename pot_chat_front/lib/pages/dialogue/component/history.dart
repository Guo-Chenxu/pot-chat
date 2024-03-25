import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/dialogue/dialogue_controller.dart';

class HistoryBody extends GetView<DialogueController> {
  HistoryBody({super.key, required this.sessionId});

  String sessionId;

  @override
  Widget build(BuildContext context) {
    return GetX<DialogueController>(
      init: controller,
      initState: (_) async {
        await controller.info(sessionId);
      },
      builder: (_) {
        return ListView.builder(
          reverse: false,
          itemCount: _.history.length,
          itemBuilder: (BuildContext context, int index) {
            final message = _.history[index];
            return Column(
              crossAxisAlignment: message.isUser()
                  ? CrossAxisAlignment.end
                  : CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.symmetric(
                      vertical: 4.0, horizontal: 8.0),
                  child: Text(
                    message.isUser() ? _.nickname : "PotChat",
                    style: const TextStyle(fontWeight: FontWeight.bold),
                  ),
                ),
                Align(
                  alignment: message.isUser()
                      ? Alignment.centerRight
                      : Alignment.centerLeft,
                  child: Container(
                    margin: const EdgeInsets.symmetric(
                        vertical: 4.0, horizontal: 8.0),
                    padding: const EdgeInsets.all(8.0),
                    decoration: BoxDecoration(
                      color: message.isUser() ? Colors.blue : Colors.grey,
                      borderRadius: BorderRadius.circular(16.0),
                    ),
                    child: Text(
                      message.content!,
                      style: const TextStyle(color: Colors.white),
                    ),
                  ),
                ),
              ],
            );
          },
        );
      },
    );
  }
}
