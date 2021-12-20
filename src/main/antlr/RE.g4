grammar RE;

regex: '0' # Null
     | '1' # Empty
     | SYMBOL # Symbol
     | regex '*' # Star
     | regex '+' # Plus
     | regex regex # Concat
     | regex '|' regex # Alt
     | '(' regex ')' # Paren
     ;

SYMBOL: [a-zA-Z];
SPACE: ' ' -> skip;