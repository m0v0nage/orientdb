/* Generated By:JJTree: Do not edit this line. ONearOperator.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

public
class ONearOperator  extends SimpleNode implements OBinaryCompareOperator {
  public ONearOperator(int id) {
    super(id);
  }

  public ONearOperator(OrientSql p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override
  public boolean execute(Object left, Object right) {
    return false;
  }

  @Override
  public String toString() {
    return "NEAR";
  }

}
/* JavaCC - OriginalChecksum=a79af9beed70f813658f38a0162320e0 (do not edit this line) */
