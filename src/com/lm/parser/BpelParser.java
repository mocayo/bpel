package com.lm.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.ode.bpel.compiler.bom.BpelObjectFactory;
import org.apache.ode.bpel.compiler.bom.Process;
import org.apache.ode.utils.StreamUtils;
import org.xml.sax.InputSource;

/************************************************************
 * BpelParser ���� bpel�ĵ��Ľ�����������Ϸ������̵�xml�ĵ���
 * �洢�����Ϣ�� Bpel Object Model
 ************************************************************/
public class BpelParser {

    private File _bpelFile;		// �洢bepl�ĵ�
    private Process _process;	// �洢�ĵ���Ӧ��process����
    
    public static BpelParser newBpelParser() {
        return new BpelParser();
    }

    private BpelParser() {
    }

    /**************************************************************
     *	���bpel�ĵ� -> Bpel Object Model �Ľ���
     *	���� bpelFile�� ָ���������bpel�ļ�
     *************************************************************/
    @SuppressWarnings("deprecation")
	public void parseBpelFile(File bpelFile) throws IOException {
    	
        if (bpelFile == null) {
            throw new IllegalArgumentException("Null bpelFile");
        }        
        _bpelFile = bpelFile;
        
        try {
            InputSource isrc = new InputSource(new ByteArrayInputStream(StreamUtils.read(_bpelFile.toURL())));
            isrc.setSystemId(bpelFile.getAbsolutePath());
            _process = BpelObjectFactory.getInstance().parse(isrc,_bpelFile.toURI());
        } catch (Exception e) {       	
        	e.printStackTrace();
            throw new IOException(e);
        }
        assert _process != null;
    }
    
   /*************************************************************
    *	getProcess: ����bpel�ĵ���Ӧ��process���� 
    *************************************************************/ 
    public Process getProcess(){  	
    	return _process;
    }
}