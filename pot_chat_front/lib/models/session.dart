import 'package:pot_chat_front/models/roleContent.dart';

class Session {
  String? sessionId;
  String? description;
  String? updateTime;
  List<RoleContent>? history;

  Session({this.sessionId, this.updateTime, this.description, this.history});

  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = <String, dynamic>{};
    data['sessionId'] = sessionId;
    data['description'] = description;
    data['updateTime'] = updateTime;
    if (history != null) {
      data['history'] = history!.map((item) => item.toJson()).toList();
    }
    return data;
  }

  Session.fromJson(Map<String, dynamic> json) {
    sessionId = json['sessionId'];
    description = json['description'];
    updateTime = json['updateTime'];
    history = json['history'] != null
        ? List<RoleContent>.from(
            json['history'].map((item) => RoleContent.fromJson(item)))
        : null;
  }

  @override
  String toString() {
    return "Session(sessionId: $sessionId, description: $description, updateTime: $updateTime, history: $history)";
  }
}
