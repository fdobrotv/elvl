package com.fdobrotv.elvl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Elvl
 */

public class Elvl   {
  @JsonProperty("isin")
  private String isin;

  @JsonProperty("price")
  private BigDecimal price;

  public Elvl isin(String isin) {
    this.isin = isin;
    return this;
  }

  /**
   * Get isin
   * @return isin
  */
  @ApiModelProperty(example = "RU000A0JX0J2", required = true, value = "")
  @NotNull

@Pattern(regexp="\\b([A-Z]{2})((?![A-Z]{10}\\b)[A-Z0-9]{10})\\b") @Size(min=12,max=12) 
  public String getIsin() {
    return isin;
  }

  public void setIsin(String isin) {
    this.isin = isin;
  }

  public Elvl price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @ApiModelProperty(example = "101.9", required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Elvl elvl = (Elvl) o;
    return Objects.equals(this.isin, elvl.isin) &&
        Objects.equals(this.price, elvl.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isin, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Elvl {\n");
    
    sb.append("    isin: ").append(toIndentedString(isin)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

