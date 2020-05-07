package com.devonfw.application.jtqj.visitormanagement.logic.api;

import java.util.List;

import org.springframework.data.domain.Page;

import com.devonfw.application.jtqj.visitormanagement.logic.api.to.VisitorEto;
import com.devonfw.application.jtqj.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;

/**
 * Interface for Visitormanagement component.
 */
public interface Visitormanagement {

  /**
   *
   * @param id
   * @return
   */
  VisitorEto getVisitor(long id);

  /**
   * Saves a visitor and store it in the database.
   *
   * @param visitor the {@link VisitorEto} to create.
   * @return the new {@link VisitorEto} that has been saved with ID and version.
   */
  VisitorEto saveVisitor(VisitorEto visitor);

  /**
   * Deletes a visitor from the database by its id 'visitorId'.
   *
   * @param visitorId Id of the visitor to delete
   * @return boolean <code>true</code> if the visitor can be deleted, <code>false</code> otherwise
   */
  boolean deleteVisitor(long visitorId);

  /**
   * Returns a paginated list of Visitors matching the search criteria.
   *
   * @param criteria the {@link VisitorSearchCriteriaTo}.
   * @return the {@link List} of matching {@link VisitorEto}s.
   */
  Page<VisitorEto> findVisitors(VisitorSearchCriteriaTo criteria);

}
