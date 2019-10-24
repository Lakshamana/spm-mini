/*
 * Created on Oct 13, 2004
 *
 */
package br.ufpa.labes.spm.exceptions;

/** @author root */
public class RepositoryQueryException extends WebapseeException {

  /** Comment for <code>serialVersionUID</code> */
  private static final long serialVersionUID = 3977859570704332337L;

  public RepositoryQueryException() {
    super();
  }

  public RepositoryQueryException(String message) {
    super(message);
  }

  public RepositoryQueryException(String message, Throwable cause) {
    super(message, cause);
  }

  public RepositoryQueryException(Throwable cause) {
    super(cause);
  }
}
