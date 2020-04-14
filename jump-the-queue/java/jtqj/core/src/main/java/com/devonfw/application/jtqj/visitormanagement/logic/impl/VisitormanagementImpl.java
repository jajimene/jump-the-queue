package com.devonfw.application.jtqj.visitormanagement.logic.impl;

import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import com.devonfw.application.jtqj.general.logic.base.AbstractComponentFacade;
import com.devonfw.application.jtqj.visitormanagement.dataaccess.api.VisitorEntity;
import com.devonfw.application.jtqj.visitormanagement.dataaccess.api.repo.VisitorRepository;
import com.devonfw.application.jtqj.visitormanagement.logic.api.Visitormanagement;
import com.devonfw.application.jtqj.visitormanagement.logic.api.to.VisitorEto;
import com.devonfw.application.jtqj.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;

/**
 * Implementation of component interface of visitormanagement
 */
@Named
public class VisitormanagementImpl extends AbstractComponentFacade implements Visitormanagement {

  @Inject
  private VisitorRepository visitorRepository;

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(VisitormanagementImpl.class);

  @Override
  public VisitorEto findVisitor(long id) {

    LOG.debug("Get Visitor with id {} from database.", id);
    Optional<VisitorEntity> foundEntity = this.visitorRepository.findById(id);
    if (foundEntity.isPresent())
      return getBeanMapper().map(foundEntity.get(), VisitorEto.class);
    else
      return null;
  }

  @Override
  public VisitorEto saveVisitor(VisitorEto visitor) {

    Objects.requireNonNull(visitor, "visitor");

    VisitorEntity visitorEntity = getBeanMapper().map(visitor, VisitorEntity.class);

    // initialize, validate visitorEntity here if necessary
    VisitorEntity resultEntity = this.visitorRepository.save(visitorEntity);
    LOG.debug("Visitor with id '{}' has been created.", resultEntity.getId());
    return getBeanMapper().map(resultEntity, VisitorEto.class);
  }

  @Override
  public boolean deleteVisitor(long visitorId) {

    VisitorEntity visitor = this.visitorRepository.find(visitorId);
    this.visitorRepository.delete(visitor);
    LOG.debug("The visitor with id '{}' has been deleted.", visitorId);
    return true;
  }

  @Override
  public Page<VisitorEto> findVisitors(VisitorSearchCriteriaTo criteria) {

    Page<VisitorEntity> visitors = this.visitorRepository.findByCriteria(criteria);
    return mapPaginatedEntityList(visitors, VisitorEto.class);
  }

}
