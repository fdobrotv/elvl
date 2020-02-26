package com.fdobrotv.elvl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * QuoteAllOf
 */

public class QuoteAllOf   {
  @JsonProperty("id")
  private UUID id;

  @JsonProperty("elvl")
  private BigDecimal elvl;

  public QuoteAllOf id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public QuoteAllOf elvl(BigDecimal elvl) {
    this.elvl = elvl;
    return this;
  }

  /**
   * Get elvl
   * @return elvl
  */
  @ApiModelProperty(example = "100.2", required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getElvl() {
    return elvl;
  }

  public void setElvl(BigDecimal elvl) {
    this.elvl = elvl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuoteAllOf quoteAllOf = (QuoteAllOf) o;
    return Objects.equals(this.id, quoteAllOf.id) &&
        Objects.equals(this.elvl, quoteAllOf.elvl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, elvl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuoteAllOf {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    elvl: ").append(toIndentedString(elvl)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

