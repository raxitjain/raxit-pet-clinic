package com.ernstyoung.raxitpetclinic.services;

import com.ernstyoung.raxitpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
