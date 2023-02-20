/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package ksql;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class users extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5781242704832227462L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"users\",\"namespace\":\"ksql\",\"fields\":[{\"name\":\"registertime\",\"type\":\"long\"},{\"name\":\"userid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"regionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"gender\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"connect.name\":\"ksql.users\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<users> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<users> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<users> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<users> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<users> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this users to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a users from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a users instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static users fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private long registertime;
  private java.lang.String userid;
  private java.lang.String regionid;
  private java.lang.String gender;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public users() {}

  /**
   * All-args constructor.
   * @param registertime The new value for registertime
   * @param userid The new value for userid
   * @param regionid The new value for regionid
   * @param gender The new value for gender
   */
  public users(java.lang.Long registertime, java.lang.String userid, java.lang.String regionid, java.lang.String gender) {
    this.registertime = registertime;
    this.userid = userid;
    this.regionid = regionid;
    this.gender = gender;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return registertime;
    case 1: return userid;
    case 2: return regionid;
    case 3: return gender;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: registertime = (java.lang.Long)value$; break;
    case 1: userid = value$ != null ? value$.toString() : null; break;
    case 2: regionid = value$ != null ? value$.toString() : null; break;
    case 3: gender = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'registertime' field.
   * @return The value of the 'registertime' field.
   */
  public long getRegistertime() {
    return registertime;
  }


  /**
   * Sets the value of the 'registertime' field.
   * @param value the value to set.
   */
  public void setRegistertime(long value) {
    this.registertime = value;
  }

  /**
   * Gets the value of the 'userid' field.
   * @return The value of the 'userid' field.
   */
  public java.lang.String getUserid() {
    return userid;
  }


  /**
   * Sets the value of the 'userid' field.
   * @param value the value to set.
   */
  public void setUserid(java.lang.String value) {
    this.userid = value;
  }

  /**
   * Gets the value of the 'regionid' field.
   * @return The value of the 'regionid' field.
   */
  public java.lang.String getRegionid() {
    return regionid;
  }


  /**
   * Sets the value of the 'regionid' field.
   * @param value the value to set.
   */
  public void setRegionid(java.lang.String value) {
    this.regionid = value;
  }

  /**
   * Gets the value of the 'gender' field.
   * @return The value of the 'gender' field.
   */
  public java.lang.String getGender() {
    return gender;
  }


  /**
   * Sets the value of the 'gender' field.
   * @param value the value to set.
   */
  public void setGender(java.lang.String value) {
    this.gender = value;
  }

  /**
   * Creates a new users RecordBuilder.
   * @return A new users RecordBuilder
   */
  public static ksql.users.Builder newBuilder() {
    return new ksql.users.Builder();
  }

  /**
   * Creates a new users RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new users RecordBuilder
   */
  public static ksql.users.Builder newBuilder(ksql.users.Builder other) {
    if (other == null) {
      return new ksql.users.Builder();
    } else {
      return new ksql.users.Builder(other);
    }
  }

  /**
   * Creates a new users RecordBuilder by copying an existing users instance.
   * @param other The existing instance to copy.
   * @return A new users RecordBuilder
   */
  public static ksql.users.Builder newBuilder(ksql.users other) {
    if (other == null) {
      return new ksql.users.Builder();
    } else {
      return new ksql.users.Builder(other);
    }
  }

  /**
   * RecordBuilder for users instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<users>
    implements org.apache.avro.data.RecordBuilder<users> {

    private long registertime;
    private java.lang.String userid;
    private java.lang.String regionid;
    private java.lang.String gender;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(ksql.users.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.registertime)) {
        this.registertime = data().deepCopy(fields()[0].schema(), other.registertime);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.userid)) {
        this.userid = data().deepCopy(fields()[1].schema(), other.userid);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.regionid)) {
        this.regionid = data().deepCopy(fields()[2].schema(), other.regionid);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.gender)) {
        this.gender = data().deepCopy(fields()[3].schema(), other.gender);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing users instance
     * @param other The existing instance to copy.
     */
    private Builder(ksql.users other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.registertime)) {
        this.registertime = data().deepCopy(fields()[0].schema(), other.registertime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.userid)) {
        this.userid = data().deepCopy(fields()[1].schema(), other.userid);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.regionid)) {
        this.regionid = data().deepCopy(fields()[2].schema(), other.regionid);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.gender)) {
        this.gender = data().deepCopy(fields()[3].schema(), other.gender);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'registertime' field.
      * @return The value.
      */
    public long getRegistertime() {
      return registertime;
    }


    /**
      * Sets the value of the 'registertime' field.
      * @param value The value of 'registertime'.
      * @return This builder.
      */
    public ksql.users.Builder setRegistertime(long value) {
      validate(fields()[0], value);
      this.registertime = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'registertime' field has been set.
      * @return True if the 'registertime' field has been set, false otherwise.
      */
    public boolean hasRegistertime() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'registertime' field.
      * @return This builder.
      */
    public ksql.users.Builder clearRegistertime() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'userid' field.
      * @return The value.
      */
    public java.lang.String getUserid() {
      return userid;
    }


    /**
      * Sets the value of the 'userid' field.
      * @param value The value of 'userid'.
      * @return This builder.
      */
    public ksql.users.Builder setUserid(java.lang.String value) {
      validate(fields()[1], value);
      this.userid = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'userid' field has been set.
      * @return True if the 'userid' field has been set, false otherwise.
      */
    public boolean hasUserid() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'userid' field.
      * @return This builder.
      */
    public ksql.users.Builder clearUserid() {
      userid = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'regionid' field.
      * @return The value.
      */
    public java.lang.String getRegionid() {
      return regionid;
    }


    /**
      * Sets the value of the 'regionid' field.
      * @param value The value of 'regionid'.
      * @return This builder.
      */
    public ksql.users.Builder setRegionid(java.lang.String value) {
      validate(fields()[2], value);
      this.regionid = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'regionid' field has been set.
      * @return True if the 'regionid' field has been set, false otherwise.
      */
    public boolean hasRegionid() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'regionid' field.
      * @return This builder.
      */
    public ksql.users.Builder clearRegionid() {
      regionid = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'gender' field.
      * @return The value.
      */
    public java.lang.String getGender() {
      return gender;
    }


    /**
      * Sets the value of the 'gender' field.
      * @param value The value of 'gender'.
      * @return This builder.
      */
    public ksql.users.Builder setGender(java.lang.String value) {
      validate(fields()[3], value);
      this.gender = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'gender' field has been set.
      * @return True if the 'gender' field has been set, false otherwise.
      */
    public boolean hasGender() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'gender' field.
      * @return This builder.
      */
    public ksql.users.Builder clearGender() {
      gender = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public users build() {
      try {
        users record = new users();
        record.registertime = fieldSetFlags()[0] ? this.registertime : (java.lang.Long) defaultValue(fields()[0]);
        record.userid = fieldSetFlags()[1] ? this.userid : (java.lang.String) defaultValue(fields()[1]);
        record.regionid = fieldSetFlags()[2] ? this.regionid : (java.lang.String) defaultValue(fields()[2]);
        record.gender = fieldSetFlags()[3] ? this.gender : (java.lang.String) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<users>
    WRITER$ = (org.apache.avro.io.DatumWriter<users>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<users>
    READER$ = (org.apache.avro.io.DatumReader<users>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.registertime);

    out.writeString(this.userid);

    out.writeString(this.regionid);

    out.writeString(this.gender);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.registertime = in.readLong();

      this.userid = in.readString();

      this.regionid = in.readString();

      this.gender = in.readString();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.registertime = in.readLong();
          break;

        case 1:
          this.userid = in.readString();
          break;

        case 2:
          this.regionid = in.readString();
          break;

        case 3:
          this.gender = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










