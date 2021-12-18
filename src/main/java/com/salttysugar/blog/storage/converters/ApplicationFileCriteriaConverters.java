package com.salttysugar.blog.storage.converters;

import com.salttysugar.blog.storage.services.ApplicationFileCriteria;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public abstract class ApplicationFileCriteriaConverters {
    public static final class ApplicationFileCriteriaToMongoCriteria implements Converter<ApplicationFileCriteria, Query> {

        @Override
        public Query convert(ApplicationFileCriteria source) {
            Query query = new Query();

            if(source.getName() != null) {
                query.addCriteria(Criteria.where("name").is(source.getName()));
            }

            if(source.getExtension() != null) {

            }
            return  query;
        }
    }
}
