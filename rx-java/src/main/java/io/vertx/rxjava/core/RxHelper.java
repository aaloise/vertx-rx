package io.vertx.rxjava.core;

import io.vertx.rx.java.UnmarshallerOperator;
import io.vertx.rxjava.core.buffer.Buffer;
import rx.Observable;

/**
 * A set of helpers for RxJava and Vert.x.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class RxHelper {

  /**
   * Returns a json unmarshaller for the specified java type as a {@link rx.Observable.Operator} instance.
   *
   * The marshaller can be used with the {@link rx.Observable#lift(rx.Observable.Operator)} method to transform
   * a {@literal Observable<Buffer>} into a {@literal Observable<T>}.
   *
   * Note that the returned observable will emit at most a single object.
   *
   * @param mappedType the type to unmarshall
   * @return the unmarshaller operator
   */
  public static <T> Observable.Operator<T, Buffer> unmarshaller(Class<T> mappedType) {
    return new UnmarshallerOperator<T, Buffer>(mappedType) {
      @Override
      public io.vertx.core.buffer.Buffer unwrap(Buffer buffer) {
        return ((io.vertx.core.buffer.Buffer) buffer.getDelegate());
      }
    };
  }
}
