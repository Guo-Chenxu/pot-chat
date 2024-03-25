class RoleContent {
  String? role;
  String? content;

  RoleContent({this.role, this.content});

  RoleContent.fromJson(Map<String, dynamic> json) {
    role = json['role'];
    content = json['content'];
  }

  Map<String, dynamic> toJson() {
    Map<String, dynamic> json = {
      'role': role,
      'content': content,
    };
    return json;
  }

  bool isUser() {
    return role == "user";
  }
}
