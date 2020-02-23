package com.fdobrotv.elvl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Quote
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-02-23T20:21:55.909566300+06:00[Asia/Almaty]")

public class Quote   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("isin")
  private String isin;

  @JsonProperty("bid")
  private Integer bid;

  @JsonProperty("ask")
  private Integer ask;

  public Quote id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Quote isin(String isin) {
    this.isin = isin;
    return this;
  }

  /**
   * Get isin
   * @return isin
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Pattern(regexp="\\b([A-Z]{2})((?![A-Z]{10}\\b)[A-Z0-9]{10})\\b") @Size(min=12,max=12) 
  public String getIsin() {
    return isin;
  }

  public void setIsin(String isin) {
    this.isin = isin;
  }

  public Quote bid(Integer bid) {
    this.bid = bid;
    return this;
  }

  /**
   * Get bid
   * @return bid
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getBid() {
    return bid;
  }

  public void setBid(Integer bid) {
    this.bid = bid;
  }

  public Quote ask(Integer ask) {
    this.ask = ask;
    return this;
  }

  /**
   * Get ask
   * @return ask
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getAsk() {
    return ask;
  }

  public void setAsk(Integer ask) {
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
    Quote quote = (Quote) o;
    return Objects.equals(this.id, quote.id) &&
        Objects.equals(this.isin, quote.isin) &&
        Objects.equals(this.bid, quote.bid) &&
        Objects.equals(this.ask, quote.ask);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, isin, bid, ask);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Quote {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

