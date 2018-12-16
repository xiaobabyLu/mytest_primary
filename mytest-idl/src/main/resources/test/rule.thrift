namespace java com.xiaobaby.myproject.user.rule

enum Symbol {
    GREATER_THAN,
    LESS_THAN,
    EQUAL,
    NOT_EQUAL,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    IN,
    BETWEEN
}

struct Rule {
    1:required string key, //字段名称
    2:required Symbol symbol, //符号
    3:required list<string> value, //字段值
    4:optional string description, //描述
    5:required i32 id //规则ID
}