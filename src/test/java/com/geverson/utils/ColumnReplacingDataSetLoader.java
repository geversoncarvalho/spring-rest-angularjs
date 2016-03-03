package com.geverson.utils;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ColumnReplacingDataSetLoader extends AbstractDataSetLoader
{
    private Map<String, Object> replacements = new ConcurrentHashMap<String, Object>();

    private ReplacementDataSet createReplacementDataSet(FlatXmlDataSet dataSet){
        System.out.println("createReplacementDataSet");
        ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
        for(Map.Entry<String, Object> entry : replacements.entrySet()){
            replacementDataSet.addReplacementObject("[" + entry.getKey() + "]", entry.getValue());
        }
        replacements.clear();
        return replacementDataSet;
    }

    @Override
    protected IDataSet createDataSet(Resource resource) throws Exception {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        InputStream inputStream = resource.getInputStream();
        IDataSet dataSet = null;
        try {
            dataSet = createReplacementDataSet(builder.build(inputStream));
        }
        finally {
            inputStream.close();
        }
        return dataSet;
    }

    @Override
    public IDataSet loadDataSet(Class<?> testClass, String location) throws Exception {
        return super.loadDataSet(testClass, location);
    }

    public void replace(String replacement, Object value){
        replacements.put(replacement, value);
    }
}