package com.lm.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.ode.bpel.compiler.bom.BpelObjectFactory;
import org.apache.ode.bpel.compiler.bom.Process;
import org.apache.ode.utils.StreamUtils;
import org.xml.sax.InputSource;

/************************************************************
 * BpelParser 进行 bpel文档的解析，分析组合服务流程的xml文档，
 * 存储相关性息于 Bpel Object Model
 ************************************************************/
public class BpelParser {

    private File _bpelFile;		// 存储bepl文档
    private Process _process;	// 存储文档对应的process对象
    
    public static BpelParser newBpelParser() {
        return new BpelParser();
    }

    private BpelParser() {
    }

    /**************************************************************
     *	完成bpel文档 -> Bpel Object Model 的解析
     *	参数 bpelFile： 指向待解析的bpel文件
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
    *	getProcess: 返回bpel文档对应的process对象 
    *************************************************************/ 
    public Process getProcess(){  	
    	return _process;
    }
}