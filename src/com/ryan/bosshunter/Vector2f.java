package com.ryan.bosshunter;

public class Vector2f {
  
	public float x;
	public float y;
	
	// Constructors
    public Vector2f() {
    	x = y = 0F;
    }
    public Vector2f( float p ) {
    	x = y = p;
    }
    public Vector2f( Vector2f v ) {
    	x = v.x;
    	y = v.y;
    }
    public Vector2f( float x, float y ) {
    	this.x = x;
    	this.y = y;
    }

    // Magnitude
    public float getMagnitude() {
    	return (float) Math.sqrt(getSquaredMagnitude());
    }
    public float getSquaredMagnitude() {
    	return x*x + y*y;
    }
    
    // Distance between two vectors
    public float getDistance( Vector2f p2 ) {
    	return (float) Math.sqrt(getSquaredDistance(p2));
    }
    public static float getDistance( Vector2f p1, Vector2f p2 ) {
    	return (float) Math.sqrt(getSquaredDistance(p1, p2));
    }
    public float getSquaredDistance( Vector2f p2 ) {
    	return Vector2f.subtract(p2, this).getSquaredMagnitude();
    }
    public static float getSquaredDistance( Vector2f p1, Vector2f p2 ) {
    	return Vector2f.subtract(p2,  p1).getSquaredMagnitude();
    }

    // Normals
    public Vector2f normalize() {
    	float mag = getMagnitude();
    	this.divide(mag);
    	return this;
    }
    public static Vector2f normal( Vector2f v1, Vector2f v2 ) {
    	float dx = v2.x - v1.x;
    	float dy = v2.y - v1.y;	  
    	return new Vector2f( -dy, dx ).normalize();
    }

    // Dot
    public float dot( Vector2f other ) {
    	return (x * other.x + y * other.y);
    }
    
    // Cross
    public float cross( Vector2f other ) {
    	return (x * other.y - y * other.x);
    }

    // Reflect
    public static Vector2f reflect( Vector2f a, Vector2f b ) {
		float dotProduct = -a.x*b.x - a.y*b.y;
		return new Vector2f(a.x + 2 * b.x * dotProduct,
							a.y + 2 * b.y * dotProduct);
    }
    public Vector2f reflect( Vector2f other ) {
    	float dotProduct = -x*other.x - y*other.y;
		x = x + 2 * other.x * dotProduct;
		y = y + 2 * other.y * dotProduct;
		return this;
    }

    // Angle Radians
    public float getAngleRadians() {
    	float angle = (float) Math.atan2( y, x );

    	if ( angle < 0 ) {
    		angle += 2 * Math.PI;
    	}

    	return angle;
    }
    
    // Angle Degrees
    public float getAngleDegrees() {
    	return getAngleRadians() * 57.29578F;
    }

    // Set
    public void set( Vector2f rhs ) {
    	this.x = rhs.x;
    	this.y = rhs.y;
    }
    public void set( float val ) {
    	this.x = val;
    	this.y = val;
    }
    public void set( float x, float y ) {
    	this.x = x;
    	this.y = y;
    }
    
    // Equals
    public boolean equals( Vector2f rhs ) {
    	return this.x == rhs.x && this.y == rhs.y;
    }
    
    // Not Equals
    public boolean notEquals( Vector2f rhs ) {
    	return ! this.equals(rhs);
    }

    // Basic Math
    public Vector2f add( Vector2f rhs ) {
    	this.x += rhs.x;
    	this.y += rhs.y;
    	return this;
    }
    public Vector2f subtract( Vector2f rhs ) {
    	this.x -= rhs.x;
    	this.y -= rhs.y;
    	return this;
    }
    public Vector2f multiply( Vector2f rhs ) {
    	this.x *= rhs.x;
    	this.y *= rhs.y;
    	return this;
    }
    public Vector2f multiply( float rhs ) {
    	this.x *= rhs;
    	this.y *= rhs;
    	return this;
    }
    public Vector2f divide( Vector2f rhs ) {
    	this.x /= rhs.x;
    	this.y /= rhs.y;
    	return this;
    }
    public Vector2f divide( float rhs ) {
    	this.x /= rhs;
    	this.y /= rhs;
    	return this;
    }
    
    // Static Math methods
    public static Vector2f add( Vector2f a, Vector2f b ) {
    	Vector2f result = new Vector2f(a);
    	return result.add(b);
    }
    public static Vector2f subtract( Vector2f a, Vector2f b ) {
    	Vector2f result = new Vector2f(a);
    	return result.subtract(b);
    }
    public static Vector2f multiply( Vector2f a, Vector2f b ) {
    	Vector2f result = new Vector2f(a);
    	return result.subtract(b);
    }
    public static Vector2f multiply( Vector2f a, float val ) {
    	Vector2f result = new Vector2f(a);
    	return result.multiply(val);
    }
    public static Vector2f divide( Vector2f a, Vector2f b ) {
    	Vector2f result = new Vector2f(a);
    	return result.divide(b);
    }
    public static Vector2f divide( Vector2f a, float val ) {
    	Vector2f result = new Vector2f(a);
    	return result.divide(val);
    }
}