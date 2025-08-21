package id.co.learn.core.common.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class CommonResponseWeb<T> {
    private String result;
    private String detail;
    private int sEcho;
    private List<T> aaData;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private String sColumns;
}
