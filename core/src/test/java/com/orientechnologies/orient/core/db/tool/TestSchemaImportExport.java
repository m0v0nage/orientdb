package com.orientechnologies.orient.core.db.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orientechnologies.orient.core.command.OCommandOutputListener;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;

public class TestSchemaImportExport {

  private static final class MockOutputListener implements OCommandOutputListener {
    @Override
    public void onMessage(String iText) {
    }
  }

  @Test
  public void testExportImportCustomData() throws IOException {
    ODatabaseDocumentTx db = new ODatabaseDocumentTx("memory:" + TestSchemaImportExport.class.getSimpleName());
    db.create();
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try {
      OClass clazz = db.getMetadata().getSchema().createClass("Test");
      clazz.setCustom("testcustom", "test");
      ODatabaseExport exp = new ODatabaseExport(db, output, new MockOutputListener());
      exp.exportDatabase();
    } finally {
      db.drop();
    }

    ODatabaseDocumentTx db1 = new ODatabaseDocumentTx("memory:imp_" + TestSchemaImportExport.class.getSimpleName());
    db1.create();
    try {
      ODatabaseImport imp = new ODatabaseImport(db1, new ByteArrayInputStream(output.toByteArray()), new MockOutputListener());
      imp.importDatabase();
      db1.close();
      db1.open("admin", "admin");
      OClass clas1 = db1.getMetadata().getSchema().getClass("Test");
      Assert.assertNotNull(clas1);
      Assert.assertEquals(clas1.getCustom("testcustom"), "test");
    } finally {
      db1.drop();
    }
  }
}
