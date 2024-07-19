package singh.maneesh.http.params;

import java.lang.reflect.Parameter;

public record PathVariable (String name, Parameter parameter){
}
