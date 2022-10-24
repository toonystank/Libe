package com.toonystank.libe.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Region {

    private final float xMin;
    private final float xMax;
    private final float yMin;
    private final float yMax;
    private final float zMin;
    private final float zMax;
    private final double xMinCentered;
    private final double xMaxCentered;
    private final double yMinCentered;
    private final double yMaxCentered;
    private final double zMinCentered;
    private final double zMaxCentered;
    private final World world;

    public Region(final Location point1, final Location point2) {
        this.xMin = Math.min(point1.getBlockX(), point2.getBlockX());
        this.xMax = Math.max(point1.getBlockX(), point2.getBlockX());
        this.yMin = Math.min(point1.getBlockY(), point2.getBlockY());
        this.yMax = Math.max(point1.getBlockY(), point2.getBlockY());
        this.zMin = Math.min(point1.getBlockZ(), point2.getBlockZ());
        this.zMax = Math.max(point1.getBlockZ(), point2.getBlockZ());
        this.world = point1.getWorld();
        this.xMinCentered = this.xMin + 0.5;
        this.xMaxCentered = this.xMax + 0.5;
        this.yMinCentered = this.yMin + 0.5;
        this.yMaxCentered = this.yMax + 0.5;
        this.zMinCentered = this.zMin + 0.5;
        this.zMaxCentered = this.zMax + 0.5;
    }

    public Iterator<Block> blockList() {
        List<Block> bL = new ArrayList<>(this.getTotalBlockSize());
        for (int x = (int) this.xMin; x <= this.xMax; ++x) {
            for (int y = (int) this.yMin; y <= this.yMax; ++y) {
                for (int z = (int) this.zMin; z <= this.zMax; ++z) {
                    Block b = this.world.getBlockAt(x, y, z);
                    bL.add(b);
                }
            }
        }
        return bL.iterator();
    }

    public Location getCenter() {
        return new Location(this.world, (this.xMax - this.xMin) / 2 + this.xMin, (this.yMax - this.yMin) / 2 + this.yMin, (this.zMax - this.zMin) / 2 + this.zMin);
    }

    public double getDistance() {
        return this.getPoint1().distance(this.getPoint2());
    }

    public double getDistanceSquared() {
        return this.getPoint1().distanceSquared(this.getPoint2());
    }

    public int getHeight() {
        return (int) (this.yMax - this.yMin + 1);
    }

    public Location getPoint1() {
        return new Location(this.world, this.xMin, this.yMin, this.zMin);
    }

    public Location getPoint2() {
        return new Location(this.world, this.xMax, this.yMax, this.zMax);
    }

    public Location getRandomLocation() {
        final Random rand = new Random();
        final float x = rand.nextInt((int) (Math.abs(this.xMax - this.xMin) + 1)) + this.xMin;
        final float y = rand.nextInt((int) (Math.abs(this.yMax - this.yMin) + 1)) + this.yMin;
        final float z = rand.nextInt((int) (Math.abs(this.zMax - this.zMin) + 1)) + this.zMin;
        return new Location(this.world, x, y, z);
    }

    public int getTotalBlockSize() {
        return this.getHeight() * this.getXWidth() * this.getZWidth();
    }

    public int getXWidth() {
        return (int) (this.xMax - this.xMin + 1);
    }

    public int getZWidth() {
        return (int) (this.zMax - this.zMin + 1);
    }

    public World getWorld() {
        return this.world;
    }

    public boolean isIn(final Location loc) {
        return loc.getWorld() == this.world && loc.getBlockX() >= this.xMin && loc.getBlockX() <= this.xMax && loc.getBlockY() >= this.yMin && loc.getBlockY() <= this.yMax && loc
                .getBlockZ() >= this.zMin && loc.getBlockZ() <= this.zMax;
    }

    public boolean isIn(final Player player) {
        return this.isIn(player.getLocation());
    }

    public boolean isInWithMarge(final Location loc, final double marge) {
        return loc.getWorld() == this.world && loc.getX() >= this.xMinCentered - marge && loc.getX() <= this.xMaxCentered + marge && loc.getY() >= this.yMinCentered - marge && loc
                .getY() <= this.yMaxCentered + marge && loc.getZ() >= this.zMinCentered - marge && loc.getZ() <= this.zMaxCentered + marge;
    }
}