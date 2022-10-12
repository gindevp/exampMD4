package com.example.exampmd4.service.nation;

import com.example.exampmd4.model.Nation;
import com.example.exampmd4.service.GeneralService;

public interface NationService extends GeneralService<Nation> {

    boolean existsByUserName(String name);
}
