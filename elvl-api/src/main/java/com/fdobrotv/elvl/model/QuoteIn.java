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
 * QuoteIn
 */

public class QuoteIn   {
  @JsonProperty("isin")
  private String isin;

  @JsonProperty("bid")
  private BigDecimal bid;

  @JsonProperty("ask")
  private BigDecimal ask;

  public QuoteIn isin(String isin) {
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

  public QuoteIn bid(BigDecimal bid) {
    this.bid = bid;
    return this;
  }

  /**
   * Get bid
   * @return bid
  */
  @ApiModelProperty(example = "100.2", value = "")

  @Valid

  public BigDecimal getBid() {
    return bid;
  }

  public void setBid(BigDecimal bid) {
    this.bid = bid;
  }

  public QuoteIn ask(BigDecimal ask) {
    this.ask = ask;
    return this;
  }

  /**
   * Get ask
   * @return ask
  */
  @ApiModelProperty(example = "101.9", required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getAsk() {
    return ask;
  }

  public void setAsk(BigDecimal ask) {
    this.ask = ask;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuoteIn quoteIn = (QuoteIn) o;
    return Objects.equals(this.isin, quoteIn.isin) &&
        Objects.equals(this.bid, quoteIn.bid) &&
        Objects.equals(this.ask, quoteIn.ask);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isin, bid, ask);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuoteIn {\n");
    
    sb.append("    isin: ").append(toIndentedString(isin)).append("\n");
    sb.append("    bid: ").append(toIndentedString(bid)).append("\n");
    sb.append("    ask: ").append(toIndentedString(ask)).append("\n");
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

