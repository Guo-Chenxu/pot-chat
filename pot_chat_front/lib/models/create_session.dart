class CreateSessionResp {
  String? sessionId;

  CreateSessionResp({
    this.sessionId,
  });

  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = <String, dynamic>{};
    data['sessionId'] = sessionId;
    return data;
  }

  CreateSessionResp.fromJson(Map<String, dynamic> json) {
    sessionId = json['sessionId'];
  }
}
