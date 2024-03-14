class User {
  String? email;
  String? nickname;
  String? avatar;

  User({this.email, this.nickname, this.avatar});

  Map<String, dynamic> toJson() {
    Map<String, dynamic> json = {
      "email": email,
      "nickname": nickname,
      "avatar": avatar,
    };
    return json;
  }

  User.fromJson(Map<String, dynamic> json) {
    email = json['email'];
    nickname = json['nickname'];
    avatar = json['avatar'];
  }
}
