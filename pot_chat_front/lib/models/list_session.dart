class ListSessionResp {
  String? sessionId;
  String? description;
  String? updateTime;

  ListSessionResp({this.sessionId, this.description, this.updateTime});

  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = {
      "sessionId": sessionId,
      "description": description,
      "updateTime": updateTime
    };
    return data;
  }

  ListSessionResp.fromJson(Map<String, dynamic> json) {
    sessionId = json['sessionId'];
    description = json['description'];
    updateTime = json['updateTime'];
  }
}
