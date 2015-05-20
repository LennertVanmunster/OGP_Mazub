package jumpingalien.programs.program;

import java.util.List;
import java.util.Map;




import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.statements.*;
import jumpingalien.programs.types.*;

@SuppressWarnings({"rawtypes", "unchecked"})

public class ProgramFactory implements IProgramFactory<Expression<?>, Statement, Type<?>, Program> {
 
	
	@Override
	public Expression<Type<?>> createReadVariable(String variableName,
			Type<?> variableType, SourceLocation sourceLocation) {
		return new ReadVariable(variableName, variableType, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createDoubleConstant(double value,
			SourceLocation sourceLocation) {
		return new DoubleConstant(value, sourceLocation);
	}

	@Override
	public Expression<BoolType> createTrue(SourceLocation sourceLocation) {
		return new True(sourceLocation);
	}

	@Override
	public Expression<BoolType> createFalse(SourceLocation sourceLocation) {
		return new False(sourceLocation);
	}

	@Override
	public Expression<GameObjectType> createNull(SourceLocation sourceLocation) {
		return new Null(sourceLocation);
	}

	@Override
	public Expression<GameObjectType> createSelf(SourceLocation sourceLocation) {
		return new Self(sourceLocation);
	}

	@Override
	public Expression<DirectionType> createDirectionConstant(
			jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new DirectionConstant(value, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createAddition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Addition(left, right, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createSubtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Subtraction(left, right, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createMultiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Multiplication(left, right, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createDivision(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Division(left, right, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new Sqrt(expr,sourceLocation);
	}

	@Override
	public Expression<DoubleType> createRandom(Expression maxValue,
			SourceLocation sourceLocation) {
		return new RandomDouble(maxValue, sourceLocation);
	}

	@Override
	public Expression<BoolType> createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new And(left, right, sourceLocation);
	}

	@Override
	public Expression<BoolType> createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Or(left, right, sourceLocation);
	}

	@Override
	public Expression<BoolType> createNot(Expression expr, SourceLocation sourceLocation) {
		return new Not(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createLessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new LessThan(left, right, sourceLocation);
	}

	@Override
	public Expression<BoolType> createLessThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new LessThanOrEqualTo(left, right, sourceLocation);
	}

	@Override
	public Expression<BoolType> createGreaterThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new GreaterThan(left, right, sourceLocation);
	}

	@Override
	public Expression<BoolType> createGreaterThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new GreaterThanOrEqualTo(left, right, sourceLocation);
	}

	@Override
	public Expression<BoolType> createEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Equals(left, right, sourceLocation);
	}

	@Override
	public Expression<BoolType> createNotEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new NotEquals(left, right, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createGetX(Expression expr, SourceLocation sourceLocation) {
		return new GetX(expr, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createGetY(Expression expr, SourceLocation sourceLocation) {
		return new GetY(expr, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createGetWidth(Expression expr,
			SourceLocation sourceLocation) {
		return new GetWidth(expr, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createGetHeight(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHeigth(expr, sourceLocation);
	}

	@Override
	public Expression<DoubleType> createGetHitPoints(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHitPoints(expr, sourceLocation);
	}

	@Override
	public Expression<TileType> createGetTile(Expression x, Expression y,
			SourceLocation sourceLocation) {
		return new GetTile(x, y, sourceLocation);
	}

	@Override
	public Expression<ObjectType<?>> createSearchObject(Expression direction,
			SourceLocation sourceLocation) {
		return new SearchObject(direction, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsMazub(Expression expr,
			SourceLocation sourceLocation) {
		return new IsMazub(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsShark(Expression expr,
			SourceLocation sourceLocation) {
		return new IsShark(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsSlime(Expression expr,
			SourceLocation sourceLocation) {
		return new IsSlime(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsPlant(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPlant(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsDead(Expression expr,
			SourceLocation sourceLocation) {
		return new IsDead(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsTerrain(Expression expr,
			SourceLocation sourceLocation) {
		return new IsTerrain(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsPassable(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPassable(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsWater(Expression expr,
			SourceLocation sourceLocation) {
		return new IsWater(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsMagma(Expression expr,
			SourceLocation sourceLocation) {
		return new IsMagma(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsAir(Expression expr, SourceLocation sourceLocation) {
		return new IsAir(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsMoving(Expression expr, Expression direction,
			SourceLocation sourceLocation) {
		return new IsMoving(expr, direction, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsDucking(Expression expr,
			SourceLocation sourceLocation) {
		return new IsDucking(expr, sourceLocation);
	}

	@Override
	public Expression<BoolType> createIsJumping(Expression expr,
			SourceLocation sourceLocation) {
		return new IsJumping(expr, sourceLocation);
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType,
			Expression value, SourceLocation sourceLocation) {
		return new Assignment(variableName, variableType, value, sourceLocation);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		return new While(condition, body, sourceLocation);
	}

	@Override
	public Statement createForEach(
			String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,
			Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		return new ForEach(variableName, variableKind, where, sort, sortDirection, body, sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new Break(sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		return new If(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createStartRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StartRun(direction, sourceLocation);
	}

	@Override
	public Statement createStopRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StopRun(direction, sourceLocation);
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		return new StartJump(sourceLocation);
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		return new StopJump(sourceLocation);
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		return new StartDuck(sourceLocation);
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		return new StopDuck(sourceLocation);
	}

	@Override
	public Statement createWait(Expression duration,
			SourceLocation sourceLocation) {
		return new Wait(duration, sourceLocation);
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		return new Skip(sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		return new Sequence(statements, sourceLocation);
	}

	@Override
	public Type<?> getDoubleType() {
		return new DoubleType();
	}

	@Override
	public Type<?> getBoolType() {
		return new BoolType();
	}

	@Override
	public Type<?> getGameObjectType() {
		return new GameObjectType();
	}

	@Override
	public Type<?> getDirectionType() {
		return new DirectionType();
	}

	@Override
	public Program createProgram(Statement mainStatement,
			Map<String, Type<?>> globalVariables) {
		return new Program(mainStatement, globalVariables);
	}



}
