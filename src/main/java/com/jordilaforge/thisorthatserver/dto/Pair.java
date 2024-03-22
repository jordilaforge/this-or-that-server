
package com.jordilaforge.thisorthatserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pair<T> {
    private final T t1, t2;

    public Pair(T t1, T t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

}
