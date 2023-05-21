package org.example.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

public class AspectDTO {

    @JsonProperty("method")
    private String method;

    @JsonProperty("result")
    private Float result;


    public AspectDTO method(String method) {
        this.method = method;
        return this;
    }

    @Schema(name = "method", example = "Some method:", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public AspectDTO result(Float result) {
        this.result = result;
        return this;
    }

    @Schema(name = "result", example = "97.3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AspectDTO stats = (AspectDTO) o;
        return Objects.equals(this.method, stats.method) &&
                Objects.equals(this.result, stats.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Stats {\n");
        sb.append("    method: ").append(toIndentedString(method)).append("\n");
        sb.append("    result: ").append(toIndentedString(result)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        StringBuilder sb = new StringBuilder();
        sb.append("class Stats {\n");
        sb.append("    method: ").append(toIndentedString(method)).append("\n");
        sb.append("    result: ").append(toIndentedString(result)).append("\n");
        sb.append("}");
        return sb.toString();
//        if (o == null) {
//            return "null";
//        }
//        return o.toString().replace("\n", "\n    ");
    }
}
