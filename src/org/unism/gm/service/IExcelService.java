package org.unism.gm.service;

import java.io.InputStream;
import java.util.List;

public interface IExcelService
{
		InputStream getExcelInputStream(List<Object[]> objList);   
}
